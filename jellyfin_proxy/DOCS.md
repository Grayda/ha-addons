This addon creates a proxy to a Jellyfin server so that you can watch your media from Home Assistant.

Note that this addon does not run Jellyfin itself.

## Configuration

### Option: `server`

The `server` option sets the address of the Jellyfin server.

This must be in the format `http[s]://host:port`. The following are valid examples:

- `http://jellyfin.local:8096`
- `http://192.168.0.101:8096`
- `https://192.168.0.101:8920`

### Option: `proxy_pass_host`

Determines whether we should pass the host we're running on (for example, 
`homeassistant.local`) to the server we're proxying to. In general, you probably
want this to be set to `true`. 

Set to `false` if the server needs to receive the host of the jellyfin instance
(not the host Home Assistant or this addon are running on). This might be the case
if your jellyfin instance is behind an SSL proxy like (Traefik or Caddy), which
needs to receive the jellyfin host in order to route the request correctly.

## Required Dependencies

- Network access to running Jellyfin server

## Support

Please [open an issue](https://github.com/grayda/ha-addons/issues/new/choose) if you need support.
