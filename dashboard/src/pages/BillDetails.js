import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

function BillDetails() {
  const { id } = useParams();
  const [bill, setBill] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get(`http://13.232.33.97/bills/${id}`)
      .then((response) => {
        if (response.data.code === 200) {
          setBill(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching bill details");
        console.error("Error fetching bill details:", error);
      });
  }, [id]);

  // Function to convert bill data to CSV
  const convertToCSV = () => {
    if (!bill) return "";

    const headers = "Field,Value\n";
    const rows = Object.keys(bill)
      .map((key) => `${key},${bill[key]}`)
      .join("\n");

    return headers + rows;
  };

  // Function to download the CSV file
  const downloadCSV = () => {
    const csvData = convertToCSV();
    const blob = new Blob([csvData], { type: "text/csv" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.setAttribute("hidden", "");
    a.setAttribute("href", url);
    a.setAttribute("download", `bill_${id}.csv`);
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  };

  return (
    <div className="container mt-4">
      <h1>Bill Details</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {bill ? (
        <div>
          <table className="table table-bordered">
            <tbody>
              <tr>
                <th>Bill Id</th>
                <td>{bill.id}</td>
              </tr>
              <tr>
                <th>Client Id</th>
                <td>{bill.clientId}</td>
              </tr>
              <tr>
                <th>Reading Date</th>
                <td>{bill.readingDate}</td>
              </tr>
              <tr>
                <th>Amount</th>
                <td>{bill.totalBill}</td>
              </tr>
              <tr>
                <th>Due Date</th>
                <td>{bill.dueDate}</td>
              </tr>
              <tr>
                <th>Status</th>
                <td>{bill.status}</td>
              </tr>
            </tbody>
          </table>
          <button onClick={downloadCSV} className="btn btn-primary mt-3">
            Export to CSV
          </button>
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}

export default BillDetails;
