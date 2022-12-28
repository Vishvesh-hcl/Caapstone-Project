import React, { useState } from "react";
import AuthService from "../../services/auth.service";

export default function Profile() {

  const [currentUser, setCurrentuser] = useState(null);
  const [userReady, setUserready] = useState(false);

  React.useEffect(() => {
    const currentUser1 = AuthService.getCurrentUser()
    console.log(currentUser1)
    if (currentUser1 == null) {
      alert("User not logged in!!")
    } else {
      setCurrentuser(currentUser1)
      setUserready(true)
    }
  }, []);

  return (
    <div className="container">
      {(userReady) ?
        <div>
          <header className="jumbotron">
            <h3>
              <strong>{currentUser.username}</strong> Profile
            </h3>
          </header>
          <p>
            <strong>First name:</strong>{" "}
            {currentUser.firstname}
          </p>
          <p>
            <strong>Last name:</strong>{" "}
            {currentUser.lastname}
          </p>
          <p>
            <strong>Email:</strong>{" "}
            {currentUser.email}
          </p>
          <strong>Role:</strong>
          <ul>
            {currentUser.roles &&
              currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
          </ul>
        </div> : null}
    </div>
  );
}
