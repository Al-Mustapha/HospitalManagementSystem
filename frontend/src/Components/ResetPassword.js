import {Button, Input} from "antd";
import {useEffect, useState} from "react";
import {ResetDoctorPassword, ResetPatientPassword} from './MyClient'
import {} from "react-router-dom";

const ResetPassword = () => {



    const [email, setEmail] = useState();

    const sendLinkToEmail = () => {
        ResetPatientPassword(email)
            .then(response => console.log(response))
            .then(error => console.log(error));

        ResetDoctorPassword(email)
            .then(response => console.log(response))
            .then(error => console.log(error))

    }

    return(
        <>

            <Input name ="email" placeholder="Enter your email address" value={email}
                   onChange={(e) => setEmail(e.target.value)}
            ></Input>
            <Button onClick={sendLinkToEmail}>SUBMIT</Button>

        </>
    )
}
export default ResetPassword;