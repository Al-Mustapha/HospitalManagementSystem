import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {viewUserProfile} from './MyClient'
import {Button, DatePicker, Input} from "antd";

const inputStyle={
    width:'200px',
}

const UserProfile = () => {


    const [isDisabled, setIsDisabled] = useState(true);

    const {userId} = useParams();
    console.log(userId)

    const [userDetails, setUserDetails] = useState({
        patientId: userId,
        firstName: "",
        middleName: "",
        lastName: "",
        dateOfBirth: null,
        email: "",
        country: "",
        state: "",
        lga: "",
        gender: "",
        address: "",
        phoneNumber: "",
        profilePhoto: ""
    });

    const [bDate, setBDate] = useState("");

    const fetchUserDetails = () => {
        viewUserProfile(userId)
            .then((response) => {
                setUserDetails(response.data)
            })
    }

    useEffect(()=> {
        fetchUserDetails();
    },[])

    console.log(userDetails)

    const enableInput = () => {
        setIsDisabled(false);
    }


    return(
        <>

            <div align="center" style={{marginTop:'100px'}}>

                <h1>Profile Info</h1>

                <div>

                <div style={{display:'flex'}}>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="firstname">Firstname: </label> <br/>
                <Input style={inputStyle} name="firstname" value={userDetails.firstName}

                disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="middlename">Middlename: </label><br/>
                <Input style={inputStyle} name="middlename" value={userDetails.middleName}

                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="lastname">Lastname: </label><br/>
                <Input style={inputStyle} name="lastname" value={userDetails.lastName}

                       disabled={isDisabled}
                ></Input>
                </div>
                </div>
                <br/>
            <div style={{display:'flex'}}>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="dateOfBirth">DOB: </label><br/>
                <DatePicker name="dateOfBirth" style={inputStyle}

                            value={bDate}
                            disabled={isDisabled}
                >{userDetails.dateOfBirth}</DatePicker>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="email">Email: </label><br/>
                <Input style={inputStyle} name="email" value={userDetails.email}

                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="country">Country: </label><br/>
                <Input style={inputStyle} name="country" value={userDetails.country}

                       disabled={isDisabled}
                ></Input>
                </div>
            </div>
                <br/>

            <div style={{display:'flex'}}>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="state">State: </label><br/>
                <Input style={inputStyle} name="state" value={userDetails.state}

                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="lga">LGA: </label><br/>
                <Input style={inputStyle} name="lga" value={userDetails.lga}

                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="gender">Gender: </label><br/>
                <Input style={inputStyle} name="gender" value={userDetails.gender}

                       disabled={isDisabled}
                ></Input>
                </div>
            </div>
                <br/>

            <div style={{display:'flex'}}>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="address">Address: </label><br/>
                <Input style={{width:'430px'}} name="address" value={userDetails.address}

                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="phone">Phone No: </label><br/>
                <Input style={inputStyle} name="phone" value={userDetails.phoneNumber}

                       disabled={isDisabled}
                ></Input>
                </div>
            </div>

                    <Button style={{width:'100px', backgroundColor:'green', color:'white'}}
                    disabled={isDisabled}
                    >SAVE</Button>
                    <Button style={{width:'100px', backgroundColor:'black', color:'white'}} onClick={enableInput}>EDIT</Button>



                </div>

            </div>
            </>
    )
}
export default UserProfile;