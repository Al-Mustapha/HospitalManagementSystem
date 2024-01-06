import {Button, Input} from "antd";
import {useEffect, useState} from "react";
import {patientChangePassword, doctorChangePassword} from './MyClient'
import {useLocation, useNavigate, useParams} from "react-router-dom";
import jwt from 'jwt-decode'
import Cookies from 'js-cookie'

const ChangePassword = () => {

    // const jwtToken = Cookies.get("myCookie");
    // const extractedClaims = jwt(jwtToken);

    const location = useLocation();

    const [token, setToken] = useState("");
    useEffect(()=> {
        const queryParameters = new URLSearchParams(location.search);
        setToken(queryParameters.get("token"))
    },[])


    const [password, setPassword] = useState(
        {
            ps1:"",
            ps2:""
        }
    );

    const changePatientPassword = () => {

            patientChangePassword(token, password.ps1, password.ps2)
                .then(response => console.log(response))
                .then(error => console.log(error))


            doctorChangePassword(token, password.ps1, password.ps2)
                .then(response => console.log(response))
                .then(error => console.log(error))

    }

    return(

        <>
        <Input name ="ps1" placeholder="Enter your new password" value={password.ps1}
        onChange={(e) => setPassword({...password, [e.target.name]:e.target.value})}
        ></Input>
        <Input name ="ps2" placeholder="Reenter your new password" value={password.ps2}
        onChange={(e) => setPassword({...password, [e.target.name]:e.target.value})}
        ></Input>
        <Button onClick={changePatientPassword}>SUBMIT</Button>
        </>

    )
}
export default ChangePassword;