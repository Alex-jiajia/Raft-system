import com.alibaba.fastjson.JSON;
import common.Cons;
import common.MRArg;
import func.MapFunc;
import func.ReduceFunc;
import java.util.Collections;
import java.util.Set;
import rpc.io.RpcNode;
import util.LogUtil;

/**
 * @author jiaying
 * @date 2023/4/12
 */
public class Worker extends RpcNode {

    private CommonMap commonMap = new CommonMap();
    private CommonReduce commonReduce = new CommonReduce();

    public void work(MapFunc mapFunc, ReduceFunc reduceFunc) {
        MRArg mrArg = requireTask(getPort());
        while (mrArg.getWorkerId() != null && !mrArg.getDone()) {
            if (mrArg.getWorkerType().equals(Cons.TASK_TYPE_MAP)) {
                commonMap.doMap(mapFunc, mrArg.getWorkerId(), mrArg.getJobFile(),
                    mrArg.getReduceNum());
            }
            if (mrArg.getWorkerType().equals(Cons.TASK_TYPE_REDUCE)) {
                commonReduce.doReduce(reduceFunc, mrArg.getWorkerId(), mrArg.getMapNum(),
                    mrArg.getReduceOutFile());
            }
            mrArg = doneTask(mrArg.getWorkerId(), mrArg.getWorkerType(), getPort());
            try {
                Thread.sleep(50L);
            } catch (Exception e) {
                LogUtil.log("not done yet, " + mrArg);
            }
        }
    }

    private MRArg requireTask(Integer port) {
        MRArg arg = JSON.parseObject(
            call(Cons.MASTER_HOST, "requireTask", new Object[]{port}).toString(),
            MRArg.class);
        return arg;
    }

    private MRArg doneTask(Integer id, Integer type, Integer port) {
        return JSON.parseObject(
            call(Cons.MASTER_HOST, "doneTask", new Object[]{id, type, port}).toString(),
            MRArg.class);
    }

    public String rpcPing(String param) {
        return "pong" + param;
    }

    public Set shutDown(String name) {
        shutDownServer();
        Thread.currentThread().interrupt();
        LogUtil.log("interrupted : " + name);
        return Collections.emptySet();
    }
}
