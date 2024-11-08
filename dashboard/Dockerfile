# Base image
FROM node:alpine as builder

# Set working directory
WORKDIR /app

# # Copy package.json and package-lock.json
# COPY package*.json ./

# Copy the rest of the app
COPY . .

# Install dependencies
RUN npm install

# Build the app
RUN npm run build

# Production image
FROM node:alpine

# Set working directory
WORKDIR /app

# Copy built app from builder stage
COPY --from=builder /app/build .

# Expose port
EXPOSE 3000

# Start the app
CMD ["npm", "start"]