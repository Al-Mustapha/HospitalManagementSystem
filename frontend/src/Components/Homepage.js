import NavBar from './NavBar'
import OurServices from "./HomepageComponents/OurServices";
import OurTeam from "./HomepageComponents/OurTeam";
import Footer from "./HomepageComponents/Footer";


import {Button} from "antd";
const Homepage = () => {
    return(
        <>
            <NavBar></NavBar>
            <br></br>
            <div>
                <img src={process.env.PUBLIC_URL + '/DesignPictures/' + "hmp2.jpg"}
                     style={{width:'90%', height:'500px'}}
                ></img>
                <div style={{position:'absolute', top:'40%', left:'10%', float:'left', textAlign:'left'}}>
                    <font size="8">GET <font color="white"><strong>QUALITY</strong></font> TREATMENT</font><br/>
                    <font size="16">at your fingertips</font><br/>
                    <Button style={{backgroundColor:'transparent', color:'white', width:'200px', height:'50px'}}>GET STARTED > </Button>
                </div>

            </div>

            <br></br>

            <OurServices></OurServices>
            <br></br>
            <OurTeam></OurTeam>
            <br></br>
            <Footer></Footer>
            </>
            )
}
export default Homepage;