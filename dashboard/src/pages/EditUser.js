import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

function EditUser() {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://13.232.33.97/users/${id}`)
      .then((response) => setUser(response.data.data))
      .catch((error) => setError("Error fetching user details"));
  }, [id]);

  const handleUpdateUser = () => {
    axios
      .put(`http://13.232.33.97/users/${id}`, user)
      .then((response) => {
        if (response.data.code === 200) {
          navigate("/users");
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => setError("Error updating user"));
  };

  if (!user) return <div>Loading...</div>;

  return (
    <div className="container mt-4">
      <h1>Edit User</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleUpdateUser();
        }}
      >
        <div className="mb-3">
          <label htmlFor="id" className="form-label">
            User ID
          </label>
          <input
            type="text"
            id="id"
            className="form-control"
            value={user.id}
            onChange={(e) => setUser({ ...user, id: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Name
          </label>
          <input
            type="text"
            id="name"
            className="form-control"
            value={user.name}
            onChange={(e) => setUser({ ...user, name: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="role" className="form-label">
            Role
          </label>
          <select
            id="role"
            className="form-select"
            value={user.role}
            onChange={(e) => setUser({ ...user, role: e.target.value })}
          >
            <option value="">Please select a role</option>
            <option value="ADMIN">ADMIN</option>
            <option value="USER">USER</option>
          </select>
        </div>
        <button type="submit" className="btn btn-success">
          Update User
        </button>
      </form>
    </div>
  );
}

export default EditUser;
