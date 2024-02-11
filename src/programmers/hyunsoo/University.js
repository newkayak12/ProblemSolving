const getTimes = ( time, direction ) => {
    const result = new Array(direction.length).fill(0)
    const stackArray = [];

    let outCount = 0;
    let inStack = []



    for (let i = 0; i < direction.length; i ++ ) {
        const dir = direction[i]
        if( dir == 1) {
            outCount++;
        } else {
            inStack.push({idx:i, time:time[i]})
        }
    }

    for (elem of inStack) {
        time[elem.idx] += outCount
        outCount = 0
    }


    return time
}

case1: {
    time = [0,0,1,5]
    direction = [0,1,1,0]

    console.log(getTimes(time, direction))
}

case2: {
    time = [0,1,1,3,3]
    direction = [0,1,0,0,1]

    // 02143
    console.log(getTimes(time, direction))
}

