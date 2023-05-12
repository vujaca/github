import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";


const MovieRow = (props) => {

    var navigate = useNavigate()

    const getGenresStringFromList = (list) => {
        return list.map(element => element.naziv).join(',');
    }

    const goToEdit = (movie) => {
        props.editCallback(movie);
        navigate('/movies/edit');
    }

    return (
        <tr>
           <td>{props.movie.naziv}</td>
           <td>{props.movie.trajanje}</td>
           <td>{props.movie.godiste}</td>
           <td>{getGenresStringFromList(props.movie.zanrovi)}</td>
           <td><Button variant="warning" onClick={() => goToEdit(props.movie)}>Edit</Button></td>
           <td><Button variant="danger" onClick={() => props.deleteMovieCallback(props.movie.id)}>Delete</Button></td>
        </tr>
     )
}

export default MovieRow;