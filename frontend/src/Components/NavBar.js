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


    const [searchKey, setSearchKey] = useState('');

    const [searchQuery, setSearchQuery] = useState([]);

    const handleSearch = () => {
        const elementsWithText = document.querySelectorAll(':not(script):not(style)');
        const matches = [];

        elementsWithText.forEach((element) => {
            if (element.textContent.toLowerCase().includes(searchKey.toLowerCase())){
                matches.push(element);
            }
        })
        setSearchQuery(matches);
    }

    const handleKeyPress = (e) => {
        if (e.key === 'Enter')
            handleSearch();
    }


    const navigate = useNavigate();

    const bookAppointment = () => {
        navigate("/bookAppointment");
    }

    const navToAppointments = () => {
        navigate("/appointments/fetchUserAppointments/" + decoded.userId);
    }

    const navToProfile = () => {
        navigate("/patient/profile/" + decoded.userId)
    }

    return(
        <>
            {/*<div>*/}
            {/*    {searchQuery.length > 0 ?*/}
            {/*        (*/}
            {/*            <ul>*/}
            {/*                {searchQuery.map((element, index) => (*/}
            {/*                    <li key={index}>{element.textContent}</li>*/}
            {/*                ))}*/}
            {/*            </ul>*/}
            {/*        ): (*/}
            {/*            <p>No matches found.</p>*/}
            {/*        )*/}
            {/*    }*/}
            {/*</div>*/}

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
                borderRadius:'10px solid white', color:'black',margin:'10px'}}
            value={searchKey}
            onChange={(e) => setSearchKey(e.target.value)}
                   onKeyDown={handleKeyPress}
            ></Input>
            <Button onClick={handleSearch}><FaSearch style={{color:'white'}}/>
            </Button>
            <Button style={{backgroundColor:'transparent', width:'150px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}
            onClick={bookAppointment}
            >Book Appointment</Button>

            <Select style={{float:'right', backgroundColor:'transparent', width:'150px',
                borderRadius:'10px solid white', color:'white',margin:'10px'}}

            >
                <Select.Option value="default" selected>Welcome, User</Select.Option>
                <Select.Option value="profile"><FaUser/> <a style={{backgroundColor:'transparent',border:'none'}} onClick={navToProfile}>Profile</a></Select.Option>
                <Select.Option value="appointments"><FaUserDoctor/> <a style={{backgroundColor:'transparent',border:'none'}} onClick={navToAppointments}>Appointments</a></Select.Option>
                <Select.Option value="notifications"><FaMessage/> <a style={{backgroundColor:'transparent',border:'none'}}>Notifications</a></Select.Option>
                <Select.Option value="logout"><FaSignOutAlt/> <a style={{backgroundColor:'transparent',border:'none'}}>Logout</a></Select.Option>
            </Select>

        </div>
        </>
    )
}
export default NavBar;