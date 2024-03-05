// 배열과 조합의 수를 인자로 전달받으며
function getCombinations(arr, selectNumber){
    let results = [];

// 만약, 조합의 수가 1이라면 재귀함수를 종료하게 되어있다
    if (selectNumber === 1)
        return arr.map((value) => [value]);

    // 배열 각각에 대한 원소를 순회한다
    arr.forEach((elem , index, origin)=>{
        // 시작 인덱스보다 하나 더 앞에서 나머지 배열을 가져온다
        const rest = origin.slice(index + 1);
        // 그 나머지 배열과 조합의수 -1의 값을 다시 재귀함수로 호출
        const combinations = getCombinations(rest, selectNumber - 1);
        const attached = combinations.map((combination) => [elem, ...combination]);
        console.log(attached)
        results.push(...attached);
    })
    results = results.map(result =>{
        result.sort();
        return result.join('');
    })
    return results;
}

console.log(getCombinations([1,4,6,7,9], 3));