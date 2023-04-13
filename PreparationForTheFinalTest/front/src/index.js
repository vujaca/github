import React,{useState} from "react";
import { createRoot } from "react-dom/client";
import { Route, Link, BrowserRouter as Router, Routes } from "react-router-dom";
import { Container, Nav, Navbar, Button } from "react-bootstrap";
import Utakmice from "./components/utakmice/Utakmice";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import {logout} from './services/auth';
import Login from './components/authorization/Login'
import AddUtakmice from "./components/utakmice/AddUtakmice";
import EditUtakmice from './components/utakmice/EditUtakmice'


const App = () => {

    const [selectedUtakmica, setSelectedUtakmica] = useState({})

    const jwt = window.localStorage.getItem("jwt")
    if (jwt){    return(
        <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                        <Nav.Link as={Link} to="/utakmice">
                            Utakmice
                        </Nav.Link>
                        <Button onClick={() => logout()}>Log out</Button>
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/utakmice" element={<Utakmice callback={(utakmica) =>setSelectedUtakmica(utakmica)}/>}/>
                        <Route path="/utakmice/add" element={<AddUtakmice/>}/>
                        <Route path="/utakmice/edit/:reprezentacijaId" element={<EditUtakmice selectedUtakmica={selectedUtakmica}/>}/>
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
                        <Nav.Link as={Link} to="/login">
                            Login
                        </Nav.Link>
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
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