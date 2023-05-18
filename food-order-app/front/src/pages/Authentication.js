import AuthForm from "../components/authorization/AuthForm"
import Axios from '../apis/Axios';
import jwt_decode from 'jwt-decode';
import { json, redirect } from 'react-router-dom';

const AuthenticationPage = () => {
    return <AuthForm/>

}
export default AuthenticationPage;

export async function action({request}) {
    const searchParams = new URL(request.url).searchParams
    const mode = searchParams.get('mode') || 'login'
    const isLogin = mode === 'login'
    if(mode !== 'login' && mode !== 'signup') {
        throw json({message: 'Unsupported mode.'}, {status: 422})
    }

    const data = await request.formData()

    let url = '/korisnici/auth'
    let cred = {
        username: data.get('username'),
        password: data.get('password')
    }

    if(data.get('name') || data.get('surname') || data.get('email')){
        cred= {
            username: data.get('username'),
            eMail: data.get('email'),
            name: data.get('name'),
            surname: data.get('surname'),
            password: data.get('password'),
            repeatedPassword: data.get('repeatedPassword')
        }
        url = '/korisnici'
    }
    

        try {
            const response = await Axios.post(url, cred)
            if(isLogin) {
                const jwt_decoded = jwt_decode(response.data)
                localStorage.setItem('jwt', response.data)
                localStorage.setItem('role', jwt_decoded.role.authority)
                const expiration = new Date()
                expiration.setHours(expiration.getHours()+1)
                localStorage.setItem('expiration', expiration.toISOString())
            }
            return redirect('/')   
        } catch (error) {
            if (error.response.status === 422|| error.response.status === 401) {
                return error.response.data
              }
            if(error.response.status === 405) {
                let response = error.response.data
                response.message='Internal Server Error!'
                return response
            }
            if(error.response.status === 400) {
                let response = error.response.data
                response.message='Wrong input! Please check your inputs!'
                return response
            }
            if (error.response.status !== 200 && !isLogin) {
                let response = error.response.data
                response.message='Username or E-mail is not unique.'
                return response
              }
              if (error.response.status >201 && error.response.status <200) {
                let response = error.response.data
                response.message='Something went wrong!'
                return response
              }
        }
    
}