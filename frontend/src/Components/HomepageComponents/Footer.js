import {Button, Input} from "antd";
import {FaFacebook, FaInstagram, FaTwitter, FaWhatsapp} from "react-icons/fa";

const Footer =() => {
    return(
        <>

            <div style={{width:'100%',height:'300px',backgroundColor:'#364d79',color:'white'}}>
                <div style={{display:'flex',alignItems:'center',justifyContent:'center'}}>
                    <div style={{width:'300px',height:'200px', backgroundColor:'transparent',color:'white',margin:'50px', textAlign:'left'}}>
                        <h3>Get Updates</h3>
                        <div><Input style={{width:'180px'}}/> <Button style={{backgroundColor:'transparent',color:'white'}}>SUBSCRIBE</Button></div><br></br>
                        <strong>Subscribe to get latest updates</strong>
                        <p>Join us to always receive a handful of latest world
                        important notifications and get yourself aware of all
                        the happenings around you.
                        </p>
                    </div>
                    <div style={{width:'300px',height:'200px', backgroundColor:'transparent',color:'white',margin:'50px', textAlign:'left'}}>
                        <h3>Contact Us</h3>
                        SW671 Efu Etsu Yisa Area Bida, Niger State<br/>
                        <br/>
                            Tel: +234 814 7558 757<br/>
                                 +234 810 9645 121
                        <br></br>
                        <Button style={{backgroundColor:'transparent',color:'white'}}>Send us a message</Button>
                    </div>
                    <div style={{width:'300px',height:'200px', backgroundColor:'transparent',color:'white',margin:'50px', textDecoration:'none'}}>
                        <a href="#" style={{textDecoration:'none', color:'white'}}>About Us</a><br></br>
                        <a href="#" style={{textDecoration:'none', color:'white'}}>Code of Ethics</a><br></br>
                        <a href="#" style={{textDecoration:'none', color:'white'}}>Resources & Links</a><br></br>
                        <a href="#" style={{textDecoration:'none', color:'white'}}>Careers</a><br></br>
                        <a href="#" style={{textDecoration:'none', color:'white'}}>News</a><br></br>
                        <br></br>
                        <h3>Follow Us</h3>
                        <FaFacebook style={{margin:'10px'}}/>
                        <FaWhatsapp style={{margin:'10px'}}/>
                        <FaInstagram style={{margin:'10px'}}/>
                        <FaTwitter style={{margin:'10px'}}/>
                    </div>


                </div>




            </div>

        </>
    )
}
export default Footer;