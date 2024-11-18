import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

function ClientDetails() {
  const { id } = useParams();
  const [client, setClient] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get(`http://13.232.33.97/users/client/${id}`)
      .then((response) => {
        if (response.data.code === 200) {
          setClient(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching client details");
        console.error("Error fetching client details:", error);
      });
  }, [id]);

  return (
    <div className="container mt-4">
      <h1>Client Details</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {client ? (
        <table className="table table-bordered">
          <tbody>
            <tr>
              <th>ID</th>
              <td>{client.id}</td>
            </tr>
            <tr>
              <th>Name</th>
              <td>{client.name}</td>
            </tr>
            <tr>
              <th>Meter Serial</th>
              <td>{client.meterSerialNumber}</td>
            </tr>
            <tr>
              <th>Category</th>
              <td>{client.category}</td>
            </tr>
            <tr>
              <th>Address</th>
              <td>{client.address}</td>
            </tr>
            <tr>
              <th>Created At</th>
              <td>{client.createdAt}</td>
            </tr>
            <tr>
              <th>Updated At</th>
              <td>{client.modifiedAt}</td>
            </tr>
            <tr>
              <th>Active</th>
              <td
                style={{
                  color: client.active ? "green" : "red",
                  fontWeight: "bold",
                }}
              >
                {client.active ? "Yes" : "No"}
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

export default ClientDetails;
