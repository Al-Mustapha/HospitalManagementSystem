import {FaFacebook, FaTwitter, FaWhatsapp, FaInstagram} from "react-icons/fa";

const imageStyle = {
    borderRadius:'150px',
}

const OurTeam = () => {
    return(
        <>
            <h1>Our Team</h1>
            <hr style={{width:'100px', borderColor:'blue'}}></hr>
        <div>
            <div style={{display:'flex', alignItems:'center', justifyContent:'center'}}>
                <div style={{margin:'40px'}}>
                    <img src={process.env.PUBLIC_URL + "/OurTeam/HALILU PASSPORT.jpg"} style={imageStyle} alt="Couldnt fetch"></img><br/>
                    <font>Dr. Muhammad Alfa</font><br/>
                    <font>Consultant</font><br/>
                    <FaFacebook style={{margin:'10px'}}/>
                    <FaWhatsapp style={{margin:'10px'}}/>
                    <FaInstagram style={{margin:'10px'}}/>
                    <FaTwitter style={{margin:'10px'}}/>
                </div>

                <div style={{margin:'40px'}}>
                    <img src={process.env.PUBLIC_URL + "/OurTeam/HALILU PASSPORT.jpg"} style={imageStyle} alt="Couldnt fetch"></img><br/>
                    <font>Dr. Muhammad Alfa</font><br/>
                    <font>Consultant</font><br/>
                    <FaFacebook style={{margin:'10px'}}/>
                    <FaWhatsapp style={{margin:'10px'}}/>
                    <FaInstagram style={{margin:'10px'}}/>
                    <FaTwitter style={{margin:'10px'}}/>
                </div>

                <div style={{margin:'40px'}}>
                    <img src={process.env.PUBLIC_URL + "/OurTeam/HALILU PASSPORT.jpg"} style={imageStyle} alt="Couldnt fetch"></img><br/>
                    <font>Dr. Muhammad Alfa</font><br/>
                    <font>Consultant</font><br/>
                    <FaFacebook style={{margin:'10px'}}/>
                    <FaWhatsapp style={{margin:'10px'}}/>
                    <FaInstagram style={{margin:'10px'}}/>
                    <FaTwitter style={{margin:'10px'}}/>
                </div>

                <div style={{margin:'40px'}}>
                    <img src={process.env.PUBLIC_URL + "/OurTeam/HALILU PASSPORT.jpg"} style={imageStyle} alt="Couldnt fetch"></img><br/>
                    <font>Dr. Muhammad Alfa</font><br/>
                    <font>Consultant</font><br/>
                    <FaFacebook style={{margin:'10px'}}/>
                    <FaWhatsapp style={{margin:'10px'}}/>
                    <FaInstagram style={{margin:'10px'}}/>
                    <FaTwitter style={{margin:'10px'}}/>
                </div>

                <div style={{margin:'40px'}}>
                    <img src={process.env.PUBLIC_URL + "/OurTeam/HALILU PASSPORT.jpg"} style={imageStyle} alt="Couldnt fetch"></img><br/>
                    <font>Dr. Muhammad Alfa</font><br/>
                    <font>Consultant</font><br/>
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
export default OurTeam;