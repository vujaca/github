import axios from 'axios';

var Axios = axios.create({
    baseURL: 'http://localhost:8080/api',

});

Axios.interceptors.request.use((config) => {
    const jwt = window.localStorage.getItem("jwt")
    if(jwt) {
        config.headers["Authorization"] = "Bearer " + jwt
    }
    return config
})

export default Axios;