// src/pages/AddClient.js

import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function AddClient() {
  const [newClient, setNewClient] = useState({
    category: "",
    name: "",
    mobileNumber: "",
    address: "",
    rrNumber: "",
    previousReading: 0,
    zoneNumber: "",
    wardNumber: "",
    existingConsumerNumber: "",
    waterBillBalance: 0,
    newMeterNumber: "",
    dateOfFixing: "",
    connectionType: "",
    buildingOwnerName: "",
    aadharNumber: "",
    municipalKhataNumber: "",
    numberOfHousesInTheBuilding: 0,
    numberOfPersonsInTheBuilding: 0,
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleAddClient = () => {
    axios
      .post("http://13.232.33.97/users/client", newClient)
      .then((response) => {
        if (response.data.code === 200) {
          alert(response.data.message);
          navigate("/clients");
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error adding client: " + error.response.data.message);
        // console.error("Error adding client:", error.response.data.message);
      });
  };

  return (
    <div className="container mt-4">
      <h1>Add New client</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleAddClient();
        }}
      >
        <div className="mb-3">
          <label htmlFor="category" className="form-label">
            Category
          </label>
          <input
            type="text"
            id="category"
            className="form-control"
            value={newClient.category}
            onChange={(e) =>
              setNewClient({ ...newClient, category: e.target.value })
            }
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
            value={newClient.name}
            onChange={(e) =>
              setNewClient({ ...newClient, name: e.target.value })
            }
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
            value={newClient.mobileNumber}
            onChange={(e) =>
              setNewClient({ ...newClient, mobileNumber: e.target.value })
            }
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
            value={newClient.address}
            onChange={(e) =>
              setNewClient({ ...newClient, address: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="rrNumber" className="form-label">
            RR Number
          </label>
          <input
            type="text"
            id="rrNumber"
            className="form-control"
            value={newClient.rrNumber}
            onChange={(e) =>
              setNewClient({ ...newClient, rrNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="zoneNumber" className="form-label">
            Zone Number
          </label>
          <input
            type="text"
            id="zoneNumber"
            className="form-control"
            value={newClient.zoneNumber}
            onChange={(e) =>
              setNewClient({ ...newClient, zoneNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="wardNumber" className="form-label">
            Ward Number
          </label>
          <input
            type="text"
            id="wardNumber"
            className="form-control"
            value={newClient.wardNumber}
            onChange={(e) =>
              setNewClient({ ...newClient, wardNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="existingConsumerNumber" className="form-label">
            Existing Consumer Number
          </label>
          <input
            type="text"
            id="existingConsumerNumber"
            className="form-control"
            value={newClient.existingConsumerNumber}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                existingConsumerNumber: e.target.value,
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="waterBillBalance" className="form-label">
            Water Bill Balance
          </label>
          <input
            type="number"
            id="waterBillBalance"
            className="form-control"
            value={newClient.waterBillBalance}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                waterBillBalance: parseFloat(e.target.value),
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="previousReading" className="form-label">
            Previous Reading
          </label>
          <input
            type="number"
            id="previousReading"
            className="form-control"
            value={newClient.previousReading}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                previousReading: parseInt(e.target.value),
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="newMeterNumber" className="form-label">
            New Meter Number
          </label>
          <input
            type="text"
            id="newMeterNumber"
            className="form-control"
            value={newClient.newMeterNumber}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                newMeterNumber: e.target.value,
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="existingConsumerNumber" className="form-label">
            Existing Consumer Number
          </label>
          <input
            type="text"
            id="existingConsumerNumber"
            className="form-control"
            value={newClient.existingConsumerNumber}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                existingConsumerNumber: e.target.value,
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="existingConsumerNumber" className="form-label">
            Existing Consumer Number
          </label>
          <input
            type="text"
            id="existingConsumerNumber"
            className="form-control"
            value={newClient.existingConsumerNumber}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                existingConsumerNumber: e.target.value,
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="dateOfFixing" className="form-label">
            Date Of Fixing
          </label>
          <input
            type="date"
            id="dateOfFixing"
            className="form-control"
            value={newClient.dateOfFixing}
            onChange={(e) =>
              setNewClient({ ...newClient, dateOfFixing: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="existingConsumerNumber" className="form-label">
            Existing Consumer Number
          </label>
          <input
            type="text"
            id="existingConsumerNumber"
            className="form-control"
            value={newClient.existingConsumerNumber}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                existingConsumerNumber: e.target.value,
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="connectionType" className="form-label">
            Connection Type
          </label>
          <input
            type="text"
            id="connectionType"
            className="form-control"
            value={newClient.connectionType}
            onChange={(e) =>
              setNewClient({ ...newClient, connectionType: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="buildingOwnerName" className="form-label">
            Building Owner Name
          </label>
          <input
            type="text"
            id="buildingOwnerName"
            className="form-control"
            value={newClient.buildingOwnerName}
            onChange={(e) =>
              setNewClient({ ...newClient, buildingOwnerName: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="aadharNumber" className="form-label">
            Aadhar Number
          </label>
          <input
            type="text"
            id="aadharNumber"
            className="form-control"
            value={newClient.aadharNumber}
            onChange={(e) =>
              setNewClient({ ...newClient, aadharNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="municipalKhataNumber" className="form-label">
            Municipal Khata Number
          </label>
          <input
            type="text"
            id="municipalKhataNumber"
            className="form-control"
            value={newClient.municipalKhataNumber}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                municipalKhataNumber: e.target.value,
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="numberOfHousesInTheBuilding" className="form-label">
            Number Of Houses In The Building
          </label>
          <input
            type="number"
            id="numberOfHousesInTheBuilding"
            className="form-control"
            value={newClient.numberOfHousesInTheBuilding}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                numberOfHousesInTheBuilding: parseInt(e.target.value),
              })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="numberOfPersonsInTheBuilding" className="form-label">
            Number Of Persons In The Building
          </label>
          <input
            type="number"
            id="numberOfPersonsInTheBuilding"
            className="form-control"
            value={newClient.numberOfPersonsInTheBuilding}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                numberOfPersonsInTheBuilding: parseInt(e.target.value),
              })
            }
            required
          />
        </div>
        <button type="submit" className="btn btn-success">
          Add client
        </button>
      </form>
    </div>
  );
}

export default AddClient;
