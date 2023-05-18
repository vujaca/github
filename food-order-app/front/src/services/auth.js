export const logout = () => {
    localStorage.removeItem('jwt')
    localStorage.removeItem('role')
    localStorage.removeItem('expiration')
    window.location.replace('/')
}

export const getTokenDuration = () => {
    const storedExpirationDate = localStorage.getItem('expiration')
    const expirationDate = new Date(storedExpirationDate)
    const now = new Date()
    const duration = expirationDate.getTime = now.getTime()
    return duration
}

export const getAuthToken = () => {
    const token = localStorage.getItem('jwt')

    if(!token) {
        return null
    }

    const tokenDuration = getTokenDuration()

    if(tokenDuration <0) {
        return 'EXPIRED'
    }

    return token;
}

export const tokenLoader = () => {
    return getAuthToken()
}