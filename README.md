# IndevNet

IndevNet is a project aimed at adding **multiplayer** support to **Minecraft Indev** by porting **Beta 1.7.3 netcode** directly into Indev. This allows Indev to connect to a Beta server. Classic’s netcode, used in IndevMP, is known for being limited and unreliable, often causing syncing problems and crashes. The goal of IndevNet is to port Beta’s netcode, which offers a more stable base for multiplayer.

## Overview

Indev was originally designed as a single-player client with no multiplayer support. Classic introduced basic multiplayer, but its netcode is unreliable. In this project, the **Beta 1.7.3 netcode** is being ported to work within Indev.

## Objectives

- Enable Indev to connect to a Beta 1.7.3 server.
- Port Beta 1.7.3 netcode with minimal changes, focusing on stability.
- Remove any unnecessary packets (like beds) later as the project progresses.

## Challenges

- **Unused Packets**: The Beta netcode includes packets for items and features that do not exist in Indev. These packets will be included but ignored for now, with plans to remove them later if they are deemed unnecessary.
- **Compatibility**: Indev’s finite world differs from Beta’s infinite worlds, so adjustments may be required to ensure a stable connection to a Beta server.
- The initial work will primarily be handled by Steve2024, but you can also contact Maxxx (Blizzardfur) if needed.

## Contributing

Contributions are welcome. If you have inquiries or would like to contribute, please reach out to steve2024 on Discord: **__nash__**. For further assistance, you can also contact Maxxx (Blizzardfur) if needed.
