import {Button, Input, Select} from "antd";
import {FaSearch, FaSignOutAlt, FaUser} from "react-icons/fa";
import {Option} from "antd/es/mentions";
import {FaMessage, FaUserDoctor} from "react-icons/fa6";
import {useNavigate} from "react-router-dom";
import Cookies from "js-cookie";
import {jwtDecode} from "jwt-decode";
import {useState} from "react";

const NavBar = () => {

    const token = Cookies.get("myCookie");
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g,'+').replace(/_/g,'/');
    const decoded = JSON.parse(atob(base64));


    const navigate = useNavigate();

    const bookAppointment = () => {
        navigate("/bookAppointment");
    }

    const navToAppointments = () => {
        navigate("/appointments/fetchUserAppointments/" + decoded.id);

    }

    return(
        <>
        <div style={{width:'100%',height:'50px', backgroundColor:'#364d79', color:'#364d79'}}>
            <Button style={{backgroundColor:'transparent', width:'100px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}>Home</Button>
            <Button style={{backgroundColor:'transparent', width:'100px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}>Service</Button>
            <Button style={{backgroundColor:'transparent', width:'100px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}>About us</Button>
            <Button style={{backgroundColor:'transparent', width:'100px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}>Contact</Button>

            <Input style={{backgroundColor:'white', width:'200px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}></Input>
            <FaSearch style={{color:'white'}}/>
            <Button style={{backgroundColor:'transparent', width:'150px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}
            onClick={bookAppointment}
            >Book Appointment</Button>

            <Select style={{float:'right', backgroundColor:'transparent', width:'150px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}

            >
                <Option value="default" selected>Welcome, User</Option>
                <Option><FaUser/> <a style={{backgroundColor:'transparent',border:'none'}}>Profile</a></Option>
                <Option><FaUserDoctor/> <a style={{backgroundColor:'transparent',border:'none'}} onClick={navToAppointments}>Appointments</a></Option>
                <Option><FaMessage/> <a style={{backgroundColor:'transparent',border:'none'}}>Notifications</a></Option>
                <Option><FaSignOutAlt/> <a style={{backgroundColor:'transparent',border:'none'}}>Logout</a></Option>
            </Select>

        </div>
        </>
    )
}
export default NavBar;