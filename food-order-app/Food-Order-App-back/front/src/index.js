import React,{useState} from "react";
import { createRoot } from "react-dom/client";
import { Route, Link, BrowserRouter as Router, Routes } from "react-router-dom";
import { Container, Nav, Navbar, Button } from "react-bootstrap";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import {logout} from './services/auth';
import Login from './components/authorization/Login';
import Linije from './components/linije/Linije'
import AddLinije from "./components/linije/AddLinije";
import EditLinije from "./components/linije/EditLinije";


const App = () => {

    const [selectedLinija, setSelectedLinija] = useState({})

    const jwt = window.localStorage.getItem("jwt")
    if (jwt){    return(
        <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                        <Nav.Link as={Link} to="/linije">
                            Linije
                        </Nav.Link>
                        <Button onClick={() => logout()}>Log out</Button>
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/linije" element={<Linije callback={(linija) =>setSelectedLinija(linija)}/>}/>
                        <Route path="/linije/add" element={<AddLinije/>}/>
                        <Route path="/linije/edit" element={<EditLinije selectedLinija={selectedLinija}/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Routes>
                </Container>
            </Router>
        
        </>
    )}
    else{
        return(
            <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                        <Nav.Link as={Link} to="/linije">
                            Linije
                        </Nav.Link>
                        <Nav.Link as={Link} to="/login">
                            Login
                        </Nav.Link>
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/linije" element={<Linije/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Routes>
                </Container>
            </Router>
        
        </>
        )
    }



}

const rootElement = document.getElementById('root')
const root = createRoot(rootElement)

root.render(
    <App/>,
);