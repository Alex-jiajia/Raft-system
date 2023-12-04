/*
 * Single.java
 * Copyright 2022 Razertory, all rights reserved.
 * GUSU PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

import func.MapFunc;
import func.ReduceFunc;
import java.util.Collections;
import java.util.List;

/**
 * @author jiaying
 * @date 2023/4/25
 */
public class Single {

    private CommonMap commonMap = new CommonMap();
    private CommonReduce commonReduce = new CommonReduce();

    public void run(MapFunc mapFunc, ReduceFunc reduceFunc, List<String> paths) {
        paths.forEach(path -> {
                commonMap.doMap(mapFunc, 0, path, 1);
            }
        );
        commonReduce.doReduce(reduceFunc, 0, 1, CommonFile.reduceOutFile(0));
        CommonFile.mergeReduceOutFiles(Collections.singletonList(CommonFile.reduceOutFile(0)));
    }
}
