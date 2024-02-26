
/**slvd**/
const findLowestPrice = ( products, discounts ) => {
    const discountMap = new Map();
    discounts.forEach( v => {
        switch ( v [1] ) {
            case '0':
            discountMap.set(v[0], (val) => v[2])
            break;
            case '1':
            discountMap.set(v[0], (val) => Math.ceil(((1 - (val / 100)) * v[2])))
            break;
            case '2':
            discountMap.set(v[0], val => val - v[2])
        }
    })
    discountMap.set("EMPTY", val => val)

    return products
    .map( elem => {
        const price = elem[0]
        let min = price;
        for ( let i = 1;  i < elem.length; i ++  ) min = Math.min(min, discountMap.get(elem[i])(price))


        return min
    })
    .reduce((p, n) => p+n, 0)
}

//
// const products = [ [ 10, "d0", "d1", "EMPTY" ], [ 15, "EMPTY", "EMPTY", "EMPTY" ], [ 20, "d2" ] ];
// const discounts = [ [ "d0", 1, 27 ], [ "d1", 2, 5 ], [ "d2", 0, 15 ] ];
// console.log(findLowestPrice(products, discounts))

const products2 = [ [ '10', 'sale', 'january-sale' ], [ '200', 'sale', 'EMPTY' ] ];
const discounts2 = [ [ 'sale', '0', '10' ], [ 'january-sale', '1', '10' ] ];
console.log(findLowestPrice(products2, discounts2))