/**
 * slvd
 * 1 -> n까지 캐싱
 * 로그 데이터 사이즈 m
 * 로그 데이터 [server_id, time]
 *
 *
 * integer x,
 * query q
 *
 * (query[i] -x , query[i])
 *
 *
 *  1-> 3
 *  integer 5
 *
 *  logData = {{1,3}, {2,6}, {1,5}}
 *  logSize = 3
 *
 *  query[] = {10, 11}
 *  query = 2 size
 *
 *  1 ~ n 까지 인덱스가 매개진다
 *  로그는 m사이즈 2차원 배열
 *  log =  { serverid, time} => 어디에서 얼마
 *
 *  x, q 각 query
 */


const getStaleServerCount = ( n , log_data, query, x ) => {
    const result = []

    for ( const q of query ) {
        const interval  = [q - x, q];
        const set = new Set();

        log_data.forEach( v => {
            if( v [1] >= interval[0] && v [1] <= interval [1] ) set.add(v[0])
        })

        result.push(n - set.size)
    }

    return result
}

console.log(getStaleServerCount(3, [[1,3], [2,6], [1,5]], [10, 11], 5))
console.log(getStaleServerCount(6, [[3,2], [4,3], [2,6], [6,3]], [3,2,6], 2))
console.log(getStaleServerCount(6, [[3,2], [4,3], [2,6], [6,3]], [1,2,3,4,5,6], 1))