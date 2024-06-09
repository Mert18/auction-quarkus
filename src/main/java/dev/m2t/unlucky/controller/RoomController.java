package dev.m2t.unlucky.controller;

import dev.m2t.unlucky.dto.BaseResponse;
import dev.m2t.unlucky.dto.request.CreateRoomRequest;
import dev.m2t.unlucky.dto.request.InviteUserRequest;
import dev.m2t.unlucky.dto.request.JoinRoomRequest;
import dev.m2t.unlucky.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin("*")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createRoom(@RequestBody CreateRoomRequest createRoomRequest, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username"); // or whatever claim holds the username
        return ResponseEntity.ok(roomService.createRoom(createRoomRequest, username));
    }

    @PostMapping("/join")
    public ResponseEntity<BaseResponse> joinRoom(@RequestBody JoinRoomRequest joinRoomRequest, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username"); // or whatever claim holds the username
        return ResponseEntity.ok(roomService.joinRoom(joinRoomRequest, username));
    }

    @PostMapping("/reject")
    public ResponseEntity<BaseResponse> rejectRoom(@RequestBody JoinRoomRequest joinRoomRequest, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username"); // or whatever claim holds the username
        return ResponseEntity.ok(roomService.rejectRoom(joinRoomRequest, username));
    }

    @PostMapping("/leave")
    public ResponseEntity<BaseResponse> leaveRoom(@RequestBody JoinRoomRequest joinRoomRequest, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username"); // or whatever claim holds the username
        return ResponseEntity.ok(roomService.leaveRoom(joinRoomRequest, username));
    }

    @PostMapping("/delete")
    public ResponseEntity<BaseResponse> deleteRoom(@RequestBody JoinRoomRequest joinRoomRequest, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username"); // or whatever claim holds the username
        return ResponseEntity.ok(roomService.deleteRoom(joinRoomRequest, username));
    }

    @PostMapping("/invite")
    public ResponseEntity<BaseResponse> inviteUser(@RequestBody InviteUserRequest inviteUserRequest, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username"); // or whatever claim holds the username
        return ResponseEntity.ok(roomService.inviteUser(inviteUserRequest, username));
    }

    @GetMapping("/list/self")
    public ResponseEntity<BaseResponse> listSelfRooms(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username"); // or whatever claim holds the username
        return ResponseEntity.ok(roomService.listSelfRooms(username));
    }

    @GetMapping("/{roomId}/owner")
    public ResponseEntity<BaseResponse> isOwner(@PathVariable String roomId, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username"); // or whatever claim holds the username
        return ResponseEntity.ok(roomService.isOwner(roomId, username));
    }
}
