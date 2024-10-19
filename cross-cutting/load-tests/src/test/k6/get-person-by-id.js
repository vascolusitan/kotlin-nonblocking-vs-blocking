import http from 'k6/http'
import { check } from 'k6'
import { options } from './config/config.js'

export { options }

export default function () {
    let personId = 1
    let res = http.get(`http://localhost:8080/api/v1/persons/${personId}`)

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response contains correct person': (r) => JSON.parse(r.body).id === personId
    })
}