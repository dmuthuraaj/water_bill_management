import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

function EditDevice() {
  const { id } = useParams();
  const [device, setDevice] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://13.232.33.97/devices/${id}`)
      .then((response) => setDevice(response.data.data))
      .catch((error) => setError("Error fetching device details"));
  }, [id]);

  const handleUpdateDevice = () => {
    axios
      .put(`http://13.232.33.97/devices/${id}`, device)
      .then((response) => {
        if (response.data.code === 200) {
          navigate("/devices");
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => setError("Error updating device"));
  };

  if (!device) return <div>Loading...</div>;

  return (
    <div className="container mt-4">
      <h1>Update Device</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleUpdateDevice();
        }}
      >
        <div className="mb-3">
          <label htmlFor="serialNumber" className="form-label">
            Serial Number
          </label>
          <input
            type="text"
            id="serialNumber"
            className="form-control"
            value={device.serialNumber}
            onChange={(e) =>
              setDevice({ ...device, serialNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="clientId" className="form-label">
            Client ID
          </label>
          <input
            type="text"
            id="clientId"
            className="form-control"
            value={device.clientId}
            onChange={(e) => setDevice({ ...device, clientId: e.target.value })}
            required
          />
        </div>
        <button type="submit" className="btn btn-success">
          Update Device
        </button>
      </form>
    </div>
  );
}

export default EditDevice;
