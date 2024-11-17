// src/pages/Users.js

import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

function Users() {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:9001/users/all")
      .then((response) => {
        if (response.data.code === 200) {
          setUsers(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching users");
        console.error("Error fetching users:", error);
      });
  }, []);

  const handleEdit = (userId) => {
    navigate(`/users/update/${userId}`);
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h1>Users</h1>
        <Link to="/add-user" className="btn btn-primary">
          Add User
        </Link>
      </div>

      {error && <div className="alert alert-danger">{error}</div>}
      <table className="table table-striped">
        <thead>
          <tr>
            <th>#</th> {/* Serial Number Column */}
            <th>User ID</th>
            <th>Mobile Number</th>
            <th>Role</th>
            <th>Created At</th>
            <th>Active</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.length > 0 ? (
            users.map((user, index) => (
              <tr key={user.id}>
                <td>{index + 1}</td>
                <td>{user.id}</td>
                <td>{user.mobileNumber}</td>
                <td>{user.role}</td>
                <td>{user.createdAt}</td>
                <td>
                  <span
                    style={{
                      color: user.active ? "green" : "red",
                      fontWeight: "bold",
                    }}
                  >
                    {user.active ? "Yes" : "No"}
                  </span>
                </td>
                <td>
                  <button
                    className="btn btn-info btn-sm"
                    onClick={() => navigate(`/users/${user.id}`)}
                  >
                    View
                  </button>
                  <button
                    className="btn btn-warning btn-sm"
                    onClick={() => handleEdit(user.id)}
                  >
                    Edit
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No users available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Users;
