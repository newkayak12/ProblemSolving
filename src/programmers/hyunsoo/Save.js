/**
 * slvd
 * <pre>
 * 처음으로 저축한 날짜부터 마지막으로 저축한 날짜까지 매일 1회 이상 저축을 했다면 1일 1저축에 성공했다고 판단합니다.
 * 예를 들어,
 *
 * 첫 번째 저축을 2021년 4월 12일 오후 4시 8분 35초에 하고,
 * 두 번째 저축을 1일 6시간 30분 뒤인 2021년 4월 13일 오후 10시 38분 35초 에 하고,
 * 마지막 저축을 1일 4시간 12분 뒤인 2021년 4월 15일 오전 2시 50분 35초 에 했다면,
 *
 *  • 4월 14일에는 저축을 하지 않았기 때문에 1일 1저축은 실패입니다.
 *  • 첫 저축 날짜는 4월 12일이고, 마지막 저축 날짜는 4월 15일이므로, 저축 기간은 4일입니다.
 *      • 저축 기간은 첫 저축 날짜부터 마지막 저축 날짜까지 포함하여
 *      그 사이 모든 날짜를 저축 유무와 상관없이 한 번씩 센 기간입니다.
 *
 * 만약,
 * 첫 번째 저축을 2821년 4월 12일 오후 4시 8분 35초 에하고,
 * 두 번째 저축을 1일 6시간 30분 뒤인 2021년 4월 13일 오후 10시 38분 35초 에 하고,
 * 마지막 저축을 0일 1시간 12분 뒤인 2021년 4월 13일 오후 11시 50분 35초에 있다면,
 *
 *  • 4월 12월부터 4월 13일까지 매일 1회 이상 저축을 했기 때문에, 1월 1저축은 성공입니다.
 *  • 첫 저속 날짜는 4월 12일이고, 마지막 저축 날짜는 4월 13일이므로, 저축 기간은 2일입니다.
 *
 * 첫 저죽을 시작한 시각인 문자열 s,
 * 다음 저축까지 걸린 기간을 담은 문자열 배열 times 가 주어집니다.
 *
 * 이때 1일 1저속 의 성공 여부와
 * 저축 기간(일)을 배열에 담아서 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 *  • 문제를 간단하게 하기 위해, 윤년은 없으며 모든 달은 30일이 마지막 날이라고 가정합니다.
 *    따라서, 1년은 360일입니다.
 *  • return 값 형식
 *     • 길이 2인 정수형 배열을 return 합니다.
 *     • [ 1일 1저속 의 성공 여부, 저축한 기간]을 return 합니다.
 *     • 첫 번째 원소로 1일 1저속에 성공했다면 1을, 성공하지 못했다면 0을 담습니다.
 *     • 두 번째 원소로 저축 기간(일)을 담습니다.
 *
 *  • s는 m:ht:0D:HH:mn: SS 형태인 문자열입니다.
 *  •YYYY 는 년도를 뜻합니다. 1년은 0001:MM:DD HH:mm:SS 형태로 주어집니다.
 *       • 1 <= YYYY <= 9999
 *  • M 은 월을 뜻합니다. 1월은 2YY: 01:00:Hti:mn:ss 형태로 주어집니다.
 *       • 1s MM S12
 *  • DD 는 일을 뜻합니다. 1일은 nrY:M:01:H:mm:SS 형태로 주어집니다.
 *       • 1s DD s30
 *  • DD는 시를 뜻합니다. 오전 1시는 YYYY:MN: DD: 01:mm: SS 형태로 주어집니다.
 *       • 03HH S23
 *  •m은 분을 뜻합니다. 1분은 YY:M:DD:H: 01:ss 형태로 주어집니다.
 *       • 0s m 559
 * • SS는 초를 뜻합니다. 1초는 rY:Ne:DD:Hl:nim: 01 형태로 주어집니다.
 *       • 05 SS 559
 * • 예를 들어, 1년 1월 1일 오전 1시 1분 1초는 0001:01:01:01:01:01 형태로 주어집니다.
 *   2021년 11월 30일 오후 10시 4분 24초는 2021:11:38:22:04:24 형태로 주어집니다.
 *</pre>
 */

const solution = (s, times = []) => {
    let saveArr = []
    let split = s.split(":").map(ele => Number(ele))
    saveArr.push(({
        date : `${split[0]}-${split[1]}-${split[2]}`,
        count: 1
    }))

    for ( let time of times ) {
        const timeSplit = time.split(":")
        split[2] += Number(timeSplit[0])
        split[3] += Number(timeSplit[1])
        split[4] += Number(timeSplit[2])
        split[5] += Number(timeSplit[3])

        split = clearUp(split)
        saveArr.push(({
            date : `${split[0]}-${split[1]}-${split[2]}`,
            count: 1
        }))
    }

    saveArr = saveArr.reduce((p, n) =>  {


        let idx =  p.findIndex(elem => {
            if ((elem?.date ?? null) == n.date) return elem
        })
        if( idx == -1) return [...p, n]
        else return p
    }, [])

    const st = saveArr[0].date.split("-")
    const ed = saveArr[saveArr.length - 1].date.split("-")
    const start = new Date();
    const end = new Date();
    start.setFullYear(st[0], st[1] - 1, st[2])
    end.setFullYear(ed[0], ed[1] - 1, ed[2])


    const totalDay = (end - start) /  (1000 * 60 * 60 * 24) + 1;
    return [totalDay ===saveArr.length ? 1 : 0, totalDay ]
}
const clearUp = (split) => {
    const result = [...split]
    for( let i = result.length - 1; i > 1; i --) {
        if( (i == 5  && result[i] >= 60) || (i == 4 && result[i] >= 60) ) {
            result[i] &= 60;

            result[i - 1] += Math.floor(result[i] / 60)
        }

        if( i == 3 && result[i] >= 24) {
            result[i] &= 24;
            result[i - 1] += Math.floor(result[i] / 24)
        }

        if( i == 2 && result[i] >= 30) {
            result[i] &= 30;
            result[i - 1] += Math.floor(result[i] / 30)
        }

        if( i == 1 && result[i] >= 12) {
            result[i] &= 12;
            result[i - 1] += Math.floor(result[i] / 12)
        }
    }

    return result
}

console.log([0,4], solution("2021:04:12:16:08:35", ['01:06:30:00', '01:04:12:00']))
console.log([1,2], solution("2021:04:12:16:08:35", ["01:06:30:00", "00:01:12:00"]))
console.log([1,2], solution("2021:04:12:16:10:42", ["01:06:30:00"]))
console.log([1,4], solution("2021:04:12:16:08:35", ["01:06:30:00", "01:01:12:00" , "00:00:09:25"]))


