import http from 'k6/http'
import { check } from 'k6'
import { options } from './config/config.js'

export { options }

export default function () {
    let newPerson = {
        name: 'John Doe',
        age: 30
    }

    let headers = { 'Content-Type': 'application/json' }

    let res = http.post('http://localhost:8080/api/v1/persons', JSON.stringify(newPerson), { headers: headers })

    check(res, { 'status is 201': (r) => r.status === 201 })
}