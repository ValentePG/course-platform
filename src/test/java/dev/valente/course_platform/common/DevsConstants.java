package dev.valente.course_platform.common;

import dev.valente.course_platform.devs.DTOs.DevsRenameDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;

import java.util.UUID;

public class DevsConstants {
    public static final Devs DEVS = new Devs("GABRIEL", "303030");
    public static final DevsResponseDTO DEVS_RESPONSE_DTO = new DevsResponseDTO(DEVS);

    public static final Devs DEVS_WITH_ID = new Devs(UUID.randomUUID(),"GABRIEL", "303030");
    public static final DevsResponseDTO DEVS_RESPONSE_DTO_WITH_ID = new DevsResponseDTO(DEVS_WITH_ID);

    public static final DevsRenameDTO DEVS_RENAME_DTO = new DevsRenameDTO("GUSTAVO");
    public static final Devs DEVS_TO_RENAME = new Devs(UUID.randomUUID(),DEVS_RENAME_DTO.userName(), "303030");
    public static final DevsResponseDTO DEVS_RENAMED_DTO = new DevsResponseDTO(DEVS_TO_RENAME);

}
