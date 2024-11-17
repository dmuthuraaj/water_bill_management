import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { Link, useNavigate } from "react-router-dom";

function DeviceDetails() {
  const { id } = useParams();
  const [device, setDevice] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://localhost:9001/devices/${id}`)
      .then((response) => {
        if (response.data.code === 200) {
          setDevice(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching device details");
        console.error("Error fetching device details:", error);
      });
  }, [id]);

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h1>Devices Details</h1>
        {/* <Link to={`/device-report/${id}`} className="btn btn-primary">
          View Reports
        </Link> */}
      </div>
      {error && <div className="alert alert-danger">{error}</div>}
      {device ? (
        <table className="table table-bordered">
          <tbody>
            <tr>
              <th>ID</th>
              <td>{device.id}</td>
            </tr>
            <tr>
              <th>Serial Number</th>
              <td>{device.serialNumber}</td>
            </tr>
            <tr>
              <th>Litres Consumed</th>
              <td>{device.totalLitresConsumed}</td>
            </tr>
            <tr>
              <th>Active</th>
              <td
                style={{
                  color: device.active ? "green" : "red",
                  fontWeight: "bold",
                }}
              >
                {device.active ? "Yes" : "No"}
              </td>
            </tr>
            <tr>
              <th>Report</th>
              <td>
                <button
                  className="btn btn-info btn-sm"
                  onClick={() => navigate(`/device-report/${device.id}`)}
                >
                  View Reports
                </button>
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

export default DeviceDetails;
