const solutionA = (num_teams, remote_tasks, office_tasks, employees) => {
    let team = new Array(employees.length)
    let isOffice = new Array(employees.length)
    let teamExist = new Array(num_teams).fill(false)


    for( let i = 0; i < employees.length; i ++  ) {
        const splt = employees[i].split(" ")
        const dept = splt[0]
        const office = splt
                         .filter((v,i) => i != 0)
                         .map(v => office_tasks.includes(v))
                         .reduce((p, n) => p || n, false)
        team[i] = dept
        isOffice[i] = office
    }

    let result = isOffice
    .map((v, i) => {
        if (!v) return i + 1
        else {
            teamExist[team[i] - 1] = true
            return -1;
        }
    })
    .filter(v => v != -1)
    .filter(v => {
        const teamGet = team[v - 1];
        const exist = teamExist[teamGet - 1]
        if( !exist ) {
            teamExist[teamGet - 1] = true
        }
        return exist
    })

    return result
}
const solutionB = (num_teams, remote_tasks, office_tasks, employees) => {
    let officeTeamMap = new Map();
    let homeTempMap  = new Map();
    let result = []

    for(let i = 0 ; i < employees.length; i++){
        let employee = employees[i];
        let split =  employee.split(" ")
        let isHome = true

        for(let j = 1 ; j < split.length; j++){
            if(office_tasks.includes(split[j].trim())) {
                isHome = false;
                break
            }
        }

        if (isHome) {
            let arr  = homeTempMap?.get(split[0]) ?? []
            arr.push(i + 1)
            homeTempMap.set(split[0], arr)
        } else {
            officeTeamMap.set(split[0], i + 1)
        }
    }


    for( const i of homeTempMap.keys()) {
        if( !officeTeamMap.get(i) ){
            const arr = homeTempMap.get(i).sort()
            arr.splice(0, 1)

            homeTempMap.set(i, arr)
        }
    }
    for ( const arr of homeTempMap.values()) {
        result = [...result, ...arr]
    }


    return result.sort()
};







const solution = (products, purchased) => {
    /*
    * How To Solve (HTS)
    * 1. 전체 products에서, 구매한 적이있는 purchased 를 빼서, unpurchased 찾기
    * 2. purchased에서 추천 목록 찾기
    * 3. unpurchased에서 purchased 맞는거 찾기
    * */

    const productsMap = new Map();
    let bought = [];
    let unpurchased = [];
    let unpurchasedProperty = [];


    for (let i = 0; i < products.length; i++) {
        const product = products[i].split(" ");
        let property = "";
        for (let j = 1; j < product.length; j++) {
            if (j !== 1) property += " ";
            property += product[j];
        }
        productsMap.set(product[0], property);
        bought.push(product[0]);
    }

    console.log(1, productsMap);
    console.log(2, bought);

    unpurchased = bought.filter(item => !purchased.includes(item));
    console.log(3, unpurchased);

    let tempArr = [];
    let purchasedPropertyMap = new Map();

    for (let i = 0; i < purchased.length; i++) {
        const purchasedItemProperty = productsMap.get(purchased[i]);

        let purchasedItemPropertySplit = purchasedItemProperty.split(" ");
        for (let j = 0; j < purchasedItemPropertySplit.length; j++) {
            tempArr.push(purchasedItemPropertySplit[j]);
            if (purchasedPropertyMap.has(purchasedItemPropertySplit[j])) {
                let value = purchasedPropertyMap.get(purchasedItemPropertySplit[j]);
                purchasedPropertyMap.set(purchasedItemPropertySplit[j], value + 1);
            } else {
                purchasedPropertyMap.set(purchasedItemPropertySplit[j], 1);
            }
        }
    }
    let tempEntries = Array.from(purchasedPropertyMap);
    // Sort the array by values first, then by keys if values are the same
    tempEntries.sort((a, b) => {
        // Compare values (a[1] and b[1])
        if (a[1] !== b[1]) {
            return b[1] - a[1]; // Sort by value
        } else {
            // If values are the same, compare keys (a[0] and b[0])
            if (a[0] > b[0]) {
                return 1;
            } else if (a[0] < b[0]) {
                return -1;
            }
        }
    });
    purchasedPropertyMap = new Map(tempEntries);
    console.log(4, purchasedPropertyMap);

    purchasedPropertyMap.forEach((value, key) => {
        for (let i = 0; i < unpurchased.length; i++) {
            let split = productsMap.get(unpurchased[i]).split(" ")
            if(split.includes(key)){
                break;
            }
        }
    });

};


console.log("result:: ", solution(3, ["development","marketing","hometask"], ["recruitment","education","officetask"], ["1 development hometask","1 recruitment marketing","2 hometask", "2 development marketing hometask","3 marketing","3 officetask","3 development"])); // [1,4,5,7]
console.log("result:: ", solution(3, ["development","marketing","hometask"], ["recruitment","education","officetask"], ["1 development hometask","1 development marketing","2 hometask", "2 development marketing hometask","3 marketing","3 officetask","3 development"])); // [1,4,5,7]
console.log("result:: ", solution(2, ["design"], ["builiding","supervise"], ["2 design","1 supervise building design", "1 design", "2 design" ])); // [3,4]
