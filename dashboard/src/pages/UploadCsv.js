import React, { useState, useEffect } from "react";
import axios from "axios";

function UploadCsv() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [message, setMessage] = useState("");

  useEffect(() => {
    if (message) {
      const timer = setTimeout(() => {
        setMessage("");
      }, 5000); // Clear message after 5 seconds

      return () => clearTimeout(timer); // Cleanup timer on unmount or message change
    }
  }, [message]);

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = () => {
    if (!selectedFile) {
      setMessage("Please select a file to upload.");
      return;
    }

    const formData = new FormData();
    formData.append("csvFile", selectedFile);

    axios
      .post("http://13.232.33.97/devices/report/upload", formData)
      .then((response) => {
        if (response.data.code === 200) {
          setMessage("File uploaded successfully.");
        } else {
          setMessage(response.data.message || "Failed to upload file.");
        }
      })
      .catch((error) => {
        setMessage("Error uploading file.");
        console.error("Error uploading file:", error);
      });
  };

  return (
    <div className="container mt-4">
      <h1>Upload CSV File</h1>
      {message && <div className="alert alert-info">{message}</div>}
      <div className="mb-3">
        <input
          type="file"
          className="form-control"
          onChange={handleFileChange}
          accept=".csv"
        />
      </div>
      {/* Note for uploading only yearly reports */}
      <div className="mb-3" style={{ color: "red" }}>
        <strong>*Note:</strong>
        <ul>
          <li>Only monthly reports can be uploaded for now.</li>
          <li>File name should be in the format of HHU_[id]_[month].csv</li>
          <li>For example: HHU_1234_January, HHU_1234_Jan</li>
        </ul>
      </div>
      <button className="btn btn-primary" onClick={handleUpload}>
        Upload
      </button>
    </div>
  );
}

export default UploadCsv;
