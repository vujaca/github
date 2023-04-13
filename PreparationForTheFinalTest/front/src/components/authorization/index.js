import React, { useState } from 'react';
import { createRoot } from 'react-dom/client';
import { Route, Link, HashRouter as Router, Routes, Navigate } from 'react-router-dom';
import { Navbar, Nav, Button, Container} from 'react-bootstrap';
import Home from './components/Home';
import Login from './components/authorization/Login'
import AddMovie from './components/movies/AddMovie';
import EditMovie from './components/movies/EditMovie';
import Movies from './components/movies/Movies';
import Projections from './components/projections/Projections';
import AddProjection from './components/projections/AddProjection';
import NotFound from './components/NotFound';
import {logout} from './services/auth';

const App = () => {

    const [selectedMovie, setSelecetedMovie] = useState({})

    const jwt = window.localStorage['jwt'];

    if(jwt){
        return (
        <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/movies">
                        Movies
                    </Nav.Link>
                    <Nav.Link as={Link} to="/projections">
                        Projections
                    </Nav.Link>
                    <Button onClick={()=>logout()}>Logout</Button>:
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                <Routes>
                    <Route path="/" element={<Home/>} />
                    <Route path="/login" element={<Navigate replace to='/'/>} />
                    <Route path="/movies" element={<Movies callback={(movie)=>setSelecetedMovie(movie)}/>} />
                    <Route path="/movies/add" element={<AddMovie/>} />
                    <Route path="/movies/edit" element={<EditMovie selectedMovie={selectedMovie}/>} />
                    <Route path="/projections" element={<Projections/>} />
                    <Route path="/projections/add" element={<AddProjection/>} />
                    <Route path="*" element={<NotFound/>} />
                </Routes>
            </Container>
            </Router>
        </>
    );
    }else{
        return( 
        <>
            <Router>
            <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/movies">
                        Movies
                    </Nav.Link>
                    <Nav.Link as={Link} to="/login">
                        Login
                    </Nav.Link>
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                <Routes>
                    <Route path="/" element={<Home/>} />
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/movies" element={<Movies/>} />
                    <Route path="*" element={<Navigate replace to = "/login"/>}/>
                </Routes>
                </Container>
            </Router>
        </>);
    }
}

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App/>,
)