import React, { useState } from "react";
import { Link } from "react-router-dom";
import UserService from "../../services/user.service";

export default function BoardAdmin() {

  const [content, setContent] = useState('');
  const [roleAdmin, setRoleAdmin] = useState(false);

  React.useEffect(() => {
    UserService.getAdminBoard().then(
      response => {
        if(response.data === "Admin Board."){
          setRoleAdmin(true)
          setContent(response.data)
        }
      },
      error => {
        setContent(
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        );
      }
    );
  }, []);


    return (
      <div className="container">
        <header className="jumbotron">
          <h3>{content}</h3>
        </header>

        {roleAdmin && (   
        <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/admin/addproduct"} className="nav-link">
                Add
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/products"} className="nav-link">
                Product List
              </Link>
            </li>
            <li className="nav-item">
              <Link to={""} className="nav-link">
                View users
              </Link>
            </li>
          </div>
          )}
      </div>
    );
}