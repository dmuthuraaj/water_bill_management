import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

function ReportDetails() {
  const { id } = useParams();
  const [reports, setReports] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get(`http://13.232.33.97/devices/report/${id}`)
      .then((response) => {
        if (response.data.code === 200) {
          setReports(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching device reports");
        console.error("Error fetching device reports:", error);
      });
  }, [id]);

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h1>Devices Reports</h1>
      </div>
      {error && <div className="alert alert-danger">{error}</div>}
      <table className="table table-striped">
        <thead>
          <tr>
            <th>#</th> {/* Serial Number Column */}
            <th>Device ID</th>
            <th>Serial Number</th>
            <th>Month</th>
            <th>Litres Consumed</th>
          </tr>
        </thead>
        <tbody>
          {reports.length > 0 ? (
            reports.map((report, index) => (
              <tr key={report.id}>
                <td>{index + 1}</td> {/* Serial Number */}
                <td>{report.deviceId}</td>
                <td>{report.serialNumber}</td>
                <td>{report.month}</td>
                <td>{report.consumed}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No reports available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ReportDetails;
