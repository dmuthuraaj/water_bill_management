import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

function EditClient() {
  const { id } = useParams();
  const [client, setClient] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://13.232.33.97/users/client/${id}`)
      .then((response) => setClient(response.data.data))
      .catch((error) => setError("Error fetching client details"));
  }, [id]);

  const handleUpdateClient = () => {
    axios
      .put(`http://13.232.33.97/users/client/${id}`, client)
      .then((response) => {
        if (response.data.code === 200) {
          navigate("/clients");
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => setError("Error updating client"));
  };

  if (!client) return <div>Loading...</div>;

  return (
    <div className="container mt-4">
      <h1>Update Client</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleUpdateClient();
        }}
      >
        <div className="mb-3">
          <label htmlFor="id" className="form-label">
            Client ID
          </label>
          <input
            type="text"
            id="id"
            className="form-control"
            value={client.id}
            onChange={(e) =>
              setClient({ ...client, id: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Client Name
          </label>
          <input
            type="text"
            id="name"
            className="form-control"
            value={client.name}
            onChange={(e) => setClient({ ...client, name: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="mobileNumber" className="form-label">
            Mobile Number
          </label>
          <input
            type="text"
            id="mobileNumber"
            className="form-control"
            value={client.mobileNumber}
            onChange={(e) => setClient({ ...client, mobileNumber: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="meterSerialNumber" className="form-label">
          Meter Serial Number
          </label>
          <input
            type="text"
            id="meterSerialNumber"
            className="form-control"
            value={client.meterSerialNumber}
            onChange={(e) => setClient({ ...client, meterSerialNumber: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="address" className="form-label">
          Address
          </label>
          <input
            type="text"
            id="address"
            className="form-control"
            value={client.address}
            onChange={(e) => setClient({ ...client, address: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="category" className="form-label">
          Category
          </label>
          <input
            type="text"
            id="category"
            className="form-control"
            value={client.category}
            onChange={(e) => setClient({ ...client, category: e.target.value })}
            required
          />
        </div>
        <button type="submit" className="btn btn-success">
          Update Client
        </button>
      </form>
    </div>
  );
}

export default EditClient;
