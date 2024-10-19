import http from 'k6/http'
import { check } from 'k6'
import { options } from './config/config.js'

export { options }

export default function () {
    let res = http.get('http://localhost:8080/api/v1/persons')

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response contains persons': (r) => JSON.parse(r.body).length > 0
    })
}