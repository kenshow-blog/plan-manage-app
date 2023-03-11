/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  swcMinify: false,
  images: {
    domains: ["openweathermap.org"],
  },
};

module.exports = nextConfig;
