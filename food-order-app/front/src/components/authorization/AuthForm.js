import { Form, Link, useActionData, useNavigation, useSearchParams } from "react-router-dom";
import classes from './AuthForm.module.css';

const AuthForm = () => {
    const navigation = useNavigation()
    const data = useActionData()

    const [searchParams] = useSearchParams()
    const isLogin = searchParams.get('mode') === 'login'
    const isSubmitting = navigation.state === 'submitting'

    return(
        <>
        <Form method="post" className={classes.form}>
        <h1>{isLogin ? 'Log in' : 'Create a new user'}</h1>
        {data && data.message && <p style={{color: 'red'}}>{data.message} </p>}
        <p>
          <label htmlFor="username">Username</label>
          <input id="username" type="text" name="username" required/>
        </p>
       {!isLogin && <p>
          <label htmlFor="email">E-mail</label>
          <input id="email" type="email" name="email" required/>
        </p>}
       {!isLogin && <p>
          <label htmlFor="name">Name</label>
          <input id="name" type="text" name="name" required/>
        </p>}
       {!isLogin && <p>
          <label htmlFor="surname">Surname</label>
          <input id="surname" type="text" name="surname" required/>
        </p>}
        <p>
          <label htmlFor="password">Password</label>
          <input id="password" type="password" name="password" required />
        </p>
       {!isLogin && <p>
          <label htmlFor="repeatedPassword">Confirm your password</label>
          <input id="repeatedPassword" type="password" name="repeatedPassword" required />
        </p>}
        <div className={classes.actions}>
          <Link to={`?mode=${isLogin ? 'signup' : 'login'}`}>
            {isLogin ? 'Create new user' : 'Login'}
          </Link>
          <button disabled={isSubmitting}>{isSubmitting ? 'Submitting...' : 'Save'}</button>
        </div>
            
            </Form>        
        </>
    )
}

export default AuthForm;