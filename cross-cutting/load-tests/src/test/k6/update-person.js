import http from 'k6/http'
import { check } from 'k6'
import { options } from './config/config.js'

export { options }

export default function () {
    let personId = 1

    let updatedPerson = {
        name: 'John Doe Updated',
        age: 31
    }

    let headers = { 'Content-Type': 'application/json' }
    let res = http.put(`http://localhost:8080/api/v1/persons/${personId}`, JSON.stringify(updatedPerson), { headers: headers })

    check(res, {
        'status is 200': (r) => r.status === 200,
        'person is updated': (r) => JSON.parse(r.body).name === 'John Doe Updated'
    })
}