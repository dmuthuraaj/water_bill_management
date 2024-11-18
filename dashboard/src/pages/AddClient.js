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

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setNewClient((prevClient) => ({
      ...prevClient,
      [id]: id === "previousReading" || id === "waterBillBalance" || id === "numberOfHousesInTheBuilding" || id === "numberOfPersonsInTheBuilding" ? parseFloat(value) : value,
    }));
  };

  const handleAddClient = (e) => {
    e.preventDefault();
    setError(null);

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
        setError("Error adding client: " + (error.response?.data?.message || error.message));
      });
  };

  return (
    <div className="container mt-4">
      <h1 className="mb-4">Add New Client</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form onSubmit={handleAddClient}>
        {[
          { label: "Category", id: "category", type: "text" },
          { label: "Name", id: "name", type: "text" },
          { label: "Mobile Number", id: "mobileNumber", type: "text" },
          { label: "Address", id: "address", type: "text" },
          { label: "RR Number", id: "rrNumber", type: "text" },
          { label: "Zone Number", id: "zoneNumber", type: "text" },
          { label: "Ward Number", id: "wardNumber", type: "text" },
          { label: "Existing Consumer Number", id: "existingConsumerNumber", type: "text" },
          { label: "Water Bill Balance", id: "waterBillBalance", type: "number" },
          { label: "Previous Reading", id: "previousReading", type: "number" },
          { label: "New Meter Number", id: "newMeterNumber", type: "text" },
          { label: "Date Of Fixing", id: "dateOfFixing", type: "date" },
          { label: "Connection Type", id: "connectionType", type: "text" },
          { label: "Building Owner Name", id: "buildingOwnerName", type: "text" },
          { label: "Aadhar Number", id: "aadharNumber", type: "text" },
          { label: "Municipal Khata Number", id: "municipalKhataNumber", type: "text" },
          { label: "Number Of Houses In The Building", id: "numberOfHousesInTheBuilding", type: "number" },
          { label: "Number Of Persons In The Building", id: "numberOfPersonsInTheBuilding", type: "number" },
        ].map(({ label, id, type }) => (
          <div className="mb-3" key={id}>
            <label htmlFor={id} className="form-label">{label}</label>
            <input
              type={type}
              id={id}
              className="form-control"
              value={newClient[id]}
              onChange={handleInputChange}
              required
            />
          </div>
        ))}
        <button type="submit" className="btn btn-primary">Add Client</button>
      </form>
    </div>
  );
}

export default AddClient;
