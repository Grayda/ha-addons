server {
    listen 8096 default_server;

    include /etc/nginx/includes/server_params.conf;

    location / {

        proxy_pass {{ .server }};
        proxy_set_header X-Ingress-Path {{ .entry }};
        
        # Disable buffering when the nginx proxy gets very resource heavy upon streaming
        proxy_buffering off;

        {{ if .proxy_pass_host }}
          proxy_set_header Host $http_host;
        {{ end }}

        include /etc/nginx/includes/proxy_params.conf;
    }

    location = / {
        return 302 {{ .server }}/web/;
    }

    # location block for /web - This is purely for aesthetics so /web/#!/ works instead of having to go to /web/index.html/#!/
    location = /web/ {
        # Proxy main Jellyfin traffic
        proxy_pass {{ .server }}/web/index.html;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header X-Forwarded-Protocol $scheme;
        proxy_set_header X-Forwarded-Host $http_host;
    }

    location /socket {
        # Proxy Jellyfin Websockets traffic
        proxy_pass {{ .server }};
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header X-Forwarded-Protocol $scheme;
        proxy_set_header X-Forwarded-Host $http_host;
    }
}
