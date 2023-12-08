server {
    listen 8096 default_server;

    include /etc/nginx/includes/server_params.conf;
    include /etc/nginx/includes/location_params.conf;

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
}
