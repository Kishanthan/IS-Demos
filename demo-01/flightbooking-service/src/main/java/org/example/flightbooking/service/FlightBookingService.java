/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.flightbooking.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This is the Microservice resource class.
 * See <a href="https://github.com/wso2/msf4j#getting-started">https://github.com/wso2/msf4j#getting-started</a>
 * for the usage of annotations.
 *
 * @since 1.0.0-SNAPSHOT
 */
@Path("/flight-booking")
public class FlightBookingService {
    private Map<String, BookingInfo> bookingInfo = new HashMap<>();

    public FlightBookingService() {
        bookingInfo.put("AA-102", createBookingInfo("AA-102"));
        bookingInfo.put("BA-324", createBookingInfo("BA-324"));
        bookingInfo.put("CH-789", createBookingInfo("CH-789"));
    }

    private BookingInfo createBookingInfo(String flightName) {
        Set<Booking> bookingList = new LinkedHashSet<>();
        bookingList.add(new Booking(1, true));
        bookingList.add(new Booking(2, true));
        bookingList.add(new Booking(3, false));
        bookingList.add(new Booking(4, true));
        bookingList.add(new Booking(5, false));
        bookingList.add(new Booking(6, true));
        bookingList.add(new Booking(7, true));
        bookingList.add(new Booking(8, false));
        bookingList.add(new Booking(9, true));
        bookingList.add(new Booking(10, true));
        return new BookingInfo(flightName, bookingList);
    }

    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        System.out.println("GET invoked");
        Response.Status statusType = Response.Status.OK;
        return Response.status(statusType).entity(bookingInfo.values()).build();
    }

    @GET
    @Path("/view/{flight-number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("flight-number") String flightName) {
        System.out.println("GET invoked");
        Response.Status statusType = Response.Status.OK;
        return Response.status(statusType).entity(bookingInfo.get(flightName)).build();
    }

    @POST
    @Path("/book")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(BookingRequest request) {
        System.out.println("POST invoked");
        Response.Status statusType = Response.Status.CREATED;
        BookingInfo bookingInfo = this.bookingInfo.get(request.getFlightName());
        if (bookingInfo != null) {
            Collection<Booking> bookings = bookingInfo.getBookings();
            if (bookings.removeIf(b -> b.getSeatNumber() == request.getSeatNumber())) {
                bookings.add(new Booking(request.getSeatNumber(), true));
            }
        }
        return Response.status(statusType).build();
    }

    @PUT
    @Path("/update")
    public void put() {
        // TODO: Implementation for HTTP PUT request
        System.out.println("PUT invoked");
    }

    @DELETE
    @Path("/remove")
    public void delete() {
        // TODO: Implementation for HTTP DELETE request
        System.out.println("DELETE invoked");
    }
}
