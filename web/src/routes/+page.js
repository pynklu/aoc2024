
export const load = async ({ fetch, params }) => {
    const res = await fetch('/api/aoc/all')
    const item = await res.text();
    return { item };
};