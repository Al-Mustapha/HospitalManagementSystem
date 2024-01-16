import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {viewUserProfile, updatePatientProfile} from './MyClient'
import {Button, DatePicker, Input} from "antd";
import locale from 'antd/lib/locale/en_US'
import moment from "moment";

const inputStyle={
    width:'200px',
}

const UserProfile = () => {


    const [isDisabled, setIsDisabled] = useState(true);

    const {userId} = useParams();

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

    const fetchUserDetails = () => {
        viewUserProfile(userId)
            .then((response) => {
                setUserDetails(response.data)
                console.log(userDetails)
            })
    }

    useEffect(()=> {
        fetchUserDetails();
    },[])

    console.log(userDetails)

    const enableInput = () => {
        setIsDisabled(false);
    }

    const updatePatientProfileInfo = () => {
        updatePatientProfile(userId, userDetails)
            .then(response => console.log(response))
    }

    const handleInputChange = (e) => {
        e.preventDefault();
        const value = e.target.value;
        setUserDetails({...userDetails, [e.target.name]: value})
    }


    return(
        <>

                <h1>Profile Info</h1>

                <div style={{display:'flex', justifyContent:'center'}}>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="firstName">Firstname: </label> <br/>
                <Input style={inputStyle} name="firstName" value={userDetails.firstName}
                onChange={handleInputChange}
                disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="middleName">Middlename: </label><br/>
                <Input style={inputStyle} name="middleName" value={userDetails.middleName}
                       onChange={handleInputChange}

                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="lastName">Lastname: </label><br/>
                <Input style={inputStyle} name="lastName" value={userDetails.lastName}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
                </div>

                <br/>
                <div style={{display:'flex', justifyContent:'center'}}>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="dateOfBirth">DOB: </label><br/>
                <Input name="dateOfBirth" style={inputStyle}
                       value={userDetails.dateOfBirth}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="email">Email: </label><br/>
                <Input style={inputStyle} name="email"
                       value={userDetails.email}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="country">Country: </label><br/>
                <Input style={inputStyle} name="country"
                       value={userDetails.country}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
            </div>
                <br/>

            <div style={{display:'flex', justifyContent:'center'}}>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="state">State: </label><br/>
                <Input style={inputStyle} name="state"
                       value={userDetails.state}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="lga">LGA: </label><br/>
                <Input style={inputStyle} name="lga"
                       value={userDetails.lga}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="gender">Gender: </label><br/>
                <Input style={inputStyle} name="gender"
                       value={userDetails.gender}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
            </div>
                <br/>

            <div style={{display:'flex', justifyContent:'center'}}>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="address">Address: </label><br/>
                <Input style={{width:'430px'}} name="address"
                       value={userDetails.address}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
                <div style={{marginLeft:'30px'}}>
                <label htmlFor="phone">Phone No: </label><br/>
                <Input style={inputStyle} name="phone"
                       value={userDetails.phoneNumber}
                       onChange={handleInputChange}
                       disabled={isDisabled}
                ></Input>
                </div>
            </div>

            <br></br>
                    <Button style={{width:'150px', backgroundColor:'green', color:'white'}}
                    disabled={isDisabled}
                    onClick={updatePatientProfileInfo}
                    >SAVE</Button>
                    <Button style={{width:'150px', backgroundColor:'black', color:'white'}}
                            onClick={enableInput}>EDIT</Button>

                        </>
    )
}
export default UserProfile;