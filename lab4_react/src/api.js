import axios from 'axios'

const url = 'http://localhost:8080/'
const api = axios.create({
    baseURL: url,
    headers: {
        'Content-Type': 'application/json'
    }
})

export async function register(user) {
    let response = await api.post('user/register', user)

    return response.data
}