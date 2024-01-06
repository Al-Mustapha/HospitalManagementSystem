import {Input, Button, } from 'antd';
import {FaClock, FaFacebook, FaLocationArrow, FaMailBulk, FaMailchimp, FaPhone} from 'react-icons/fa'
import {FaLocationDot} from "react-icons/fa6";
import {useState} from "react";
import {sendMessage} from "./Client";
const Contact = () => {

    const [message, setMessage] = useState({
            name: "",
            email:"",
            phone:"",
            message:""

        }
    );


    const sendYourMessage = () => {
        sendMessage(message)
            .then(response => console.log(response))
            .then(error => console.log(error))
    }

    return (
        <>
            <div>
                <h1>SEND US A MESSAGE</h1><br></br>
                <Input placeholder="Name" style={{width:'500px'}} name="name"
                       value={message.name}
                       onChange={(e)=>setMessage({...message, [e.target.name]:e.target.value})}
                ></Input><br/>
                <Input placeholder="Email" style={{width:'240px'}} name="email"
                       value={message.email}
                       onChange={(e) => setMessage({...message, [e.target.name]: e.target.value})}
                ></Input>
                <Input placeholder="Phone" style={{width:'240px'}} name="phone"
                       value={message.phone}
                       onChange={(e) => setMessage({...message, [e.target.name]: e.target.value})}
                ></Input><br/>
                <textarea placeholder="Your message" style={{width:'500px',height:'200px'}} name="message"
                          value={message.message}
                          onChange={(e) => setMessage({...message, [e.target.name]: e.target.value})}
                ></textarea><br></br><br></br>
                <Button style={{backgroundColor:'darkslategrey',color:'white',width:'200px',height:'50px',borderRadius:'10px'}} onClick={sendYourMessage}>Send Message</Button>
            </div>

            <br></br>


            <div style={{display:'flex',alignItems:'center',justifyContent:'center'}}>
                <div style={{margin:'50px'}}>
                    <FaLocationDot style={{width:'100px',height:'100px',color:'darkslategrey'}}></FaLocationDot><br></br>
                    <strong>LOCATION</strong><br></br>
                    Bida, Niger State
                </div>

                <div style={{margin:'50px'}}>
                    <FaClock style={{width:'100px',height:'100px',color:'darkslategrey'}}></FaClock><br></br>
                    <strong>WORKING TIME</strong><br></br>
                    Monday - Saturday
                </div>

                <div style={{margin:'50px'}}>
                    <FaPhone style={{width:'100px',height:'100px',color:'darkslategrey'}}></FaPhone><br></br>
                    <strong>CALL US</strong><br></br>
                    + 234 8147 558 757
                </div>

                <div style={{margin:'50px'}}>
                    <FaMailBulk style={{width:'100px',height:'100px',color:'darkslategrey'}}></FaMailBulk><br></br>
                    <strong>EMAIL-US</strong><br></br>
                    malfa08147558757@gmail.com
                </div>

            </div>
        </>
    )
}
export default Contact;