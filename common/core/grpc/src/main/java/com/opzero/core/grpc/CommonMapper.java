// package com.opzero.core.grpc;

// import com.opzero.core.grpc.CommonProto;

// public class CommonMapper {

//     public static CommonProto.CommonResponse map(String requestId, boolean isError, int code, String message) {
//         CommonProto.CommonResponse.Builder builder = CommonProto.CommonResponse.newBuilder();
//         builder.setRequestId(requestId);
//         builder.setIsError(isError);
//         builder.setCode(code);
//         builder.setMessage(message);
//         return builder.build();
//     }

//     public static CommonProto.CommonRequest map(String requestId) {
//         CommonProto.CommonRequest.Builder builder = CommonProto.CommonRequest.newBuilder();
//         builder.setRequestId(requestId);
//         return builder.build();
//     }
// }
