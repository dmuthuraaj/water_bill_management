import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

function UserDetails() {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.get(`http://localhost:9001/users/${id}`)
      .then(response => {
        if (response.data.code === 200) {
          setUser(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch(error => {
        setError('Error fetching user details');
        console.error('Error fetching user details:', error);
      });
  }, [id]);

  return (
    <div className="container mt-4">
      <h1>User Details</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {user ? (
        <table className="table table-bordered">
          <tbody>
            <tr>
              <th>Username</th>
              <td>{user.userName}</td>
            </tr>
            <tr>
              <th>Phone</th>
              <td>{user.mobile}</td>
            </tr>
            <tr>
              <th>Email</th>
              <td>{user.email}</td>
            </tr>
            <tr>
              <th>ID</th>
              <td>{user.id}</td>
            </tr>
            <tr>
              <th>Active</th>
              <td style={{ color: user.ative ? 'green' : 'red', fontWeight: 'bold' }}>
                {user.active ? 'Yes' : 'No'}
              </td>
            </tr>
          </tbody>
        </table>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}

export default UserDetails;
